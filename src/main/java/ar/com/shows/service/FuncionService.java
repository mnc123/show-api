package ar.com.shows.service;

import ar.com.shows.filter.FuncionFilter;
import ar.com.shows.filter.ShowOrder;
import ar.com.shows.model.Funcion;
import ar.com.shows.model.ButacaTicket;
import ar.com.shows.repository.FuncionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.convert.QueryByExamplePredicateBuilder;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
@CacheConfig(cacheNames = {"funciones"})
public class FuncionService {

    private static final Logger logger = Logger.getLogger(FuncionService.class.getName());
    private static String atributoFecha = "fecha";

    @Autowired
    FuncionRepository funcionRepository;

    // Utilizar CacheEvict cuando se agrega o modifica una funcion
    @Cacheable(cacheNames = {"funciones"})
    public List getFunciones(Funcion funcion,
                             FuncionFilter funcionFilter,
                             ShowOrder showOrder,
                             Integer pageNumber,
                             Integer size) {
        Example<Funcion> example = Example.of(funcion);

        Pageable page = PageRequest.of(pageNumber, size);
        Specification specification = Specification.where(getSpecFromFuncion(example, funcionFilter, showOrder));
        Page response = funcionRepository.findAll(specification, page);
        return response.getContent();
    }

    public Optional<Funcion> getFuncionesById(Long id) {
        return funcionRepository.findById(id);
    }

    private static <T> Specification getSpecFromFuncion(Example<T> example,
                                                        FuncionFilter funcionFilter,
                                                        ShowOrder showOrder) {
        return (Specification<T>) (root, query, builder) -> {

            final List<Predicate> predicates = new ArrayList();

            filterFecha(funcionFilter, root, builder, predicates);
            filterPrecio(funcionFilter, root, builder, query, predicates);
            order(showOrder, root, builder, query);

            predicates.add(QueryByExamplePredicateBuilder.getPredicate(root, builder, example));
            return builder.and((predicates.toArray(new Predicate[predicates.size()])));
        };
    }

    private static void filterFecha(FuncionFilter funcionFilter,
                                    From root,
                                    CriteriaBuilder builder,
                                    List<Predicate> predicates) {
        if(funcionFilter.getFechaDesde() != null && funcionFilter.getFechaHasta() != null) {
            predicates.add(builder.between(root.get(atributoFecha), funcionFilter.getFechaDesde(), funcionFilter.getFechaHasta()));
        } else if(funcionFilter.getFechaDesde() != null) {
            predicates.add(builder.greaterThan(root.get(atributoFecha), funcionFilter.getFechaDesde()));
        } else if(funcionFilter.getFechaHasta() != null) {
            predicates.add(builder.lessThan(root.get(atributoFecha), funcionFilter.getFechaHasta()));
        }
    }

    private static void filterPrecio(FuncionFilter funcionFilter,
                                     From root,
                                     CriteriaBuilder builder,
                                     CriteriaQuery query,
                                     List<Predicate> predicates) {
        if(funcionFilter.getPrecioDesde() != null && funcionFilter.getPrecioHasta() != null) {
            Join<Funcion, ButacaTicket> join = root.join("butacaTickets", JoinType.INNER);
            query.distinct(true);
            predicates.add(builder.between(join.get("precio"), funcionFilter.getPrecioDesde(), funcionFilter.getPrecioHasta()));
        }
    }

    private static void order(ShowOrder showOrder,
                              Path root,
                              CriteriaBuilder builder,
                              CriteriaQuery query) {

        if(showOrder.isAsc() || showOrder.isDesc()) {
            Path orderRoot = root;

            for(String v : showOrder.getOrderEntity()) {
                orderRoot = orderRoot.get(v);
            }

            Order order = showOrder.isAsc() ? builder.asc(orderRoot) : builder.desc(orderRoot);
            query.orderBy(order);
        }
    }


}

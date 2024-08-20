package com.binar.batch7.serviceimpl;

import lombok.extern.slf4j.Slf4j;
import com.binar.batch7.config.Config;
import com.binar.batch7.dto.OrderDetailRequest;
import com.binar.batch7.dto.OrderDetailResponse;
import com.binar.batch7.dto.OrderRequest;
import com.binar.batch7.dto.OrderResponse;
import com.binar.batch7.entity.Order;
import com.binar.batch7.entity.OrderDetail;
import com.binar.batch7.entity.Product;
import com.binar.batch7.entity.User;
import com.binar.batch7.mapper.OrderMapper;
import com.binar.batch7.repository.OrderDetailRepository;
import com.binar.batch7.repository.OrderRepository;
import com.binar.batch7.repository.ProductRepository;
import com.binar.batch7.repository.UserRepository;
import com.binar.batch7.service.OrderService;
import com.binar.batch7.service.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ValidationService validationService;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private Config config;

    @Override
    public OrderResponse create(OrderRequest request, Principal principal) {
        validationService.validate(request);
        Order order = new Order();
        order.setOrderTime(LocalDateTime.now());
        order.setIsComplete(request.getIsComplete());
        order.setDestinationAddress(request.getDestinationAddress());
        User user = userRepository.findByUsername(principal.getName());
        order.setUser(user);
        orderRepository.save(order);
        return orderMapper.toOrderResponse(order);
    }

    @Override
    public List<OrderResponse> findAll() {
        List<OrderResponse> response = new ArrayList<OrderResponse>();
        orderRepository.findAll().forEach(order -> {
            log.info("ORDER {}", order);
            response.add(orderMapper.toOrderResponse(order));
        });
        return response;
    }

    @Override
    @Transactional
    public OrderResponse update(UUID id, OrderRequest request) {
        validationService.validate(request);

        log.info("REQUEST : {}", request);
        Order order = orderRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "ID Order not found"));

        if (Objects.nonNull(request.getDestinationAddress())) {
            order.setDestinationAddress(request.getDestinationAddress());
        }

        if (Objects.nonNull(request.getIsComplete())) {
            order.setIsComplete(request.getIsComplete());
        }

        order.setUser(order.getUser());

        orderRepository.save(order);
        log.info("REQUEST : {}", orderRepository.save(order));

        return orderMapper.toOrderResponse(order);
    }

    @Override
    @Transactional
    public OrderResponse delete(UUID id) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "ID Order not found"));
        orderRepository.delete(order);
        return orderMapper.toOrderResponse(order);
    }

    @Override
    public Page<OrderDetailResponse> findAllDetails(Pageable pageable) {
        return orderDetailRepository.findAll(pageable).map(orderDetail -> orderMapper.toOrderDetailResponse(orderDetail));
    }

    @Override
    @Transactional
    public OrderDetailResponse createDetail(OrderDetailRequest request) {
        validationService.validate(request);

        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setQuantity(request.getQuantity());
        orderDetail.setTotalPrice(request.getTotalPrice());

        Order order = orderRepository.findById(config.isValidUUID(request.getOrderId())).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found with id " + request.getOrderId()));
        orderDetail.setOrder(order);

        Product product = productRepository.findById(config.isValidUUID(request.getProductId())).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found with id " + request.getProductId()));
        orderDetail.setProduct(product);

        orderDetailRepository.save(orderDetail);

        return orderMapper.toOrderDetailResponse(orderDetail);
    }
}

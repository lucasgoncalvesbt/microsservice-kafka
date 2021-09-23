package one.digitalinnovation.ecommerce.checkout.listener;

import one.digitalinnovation.ecommerce.checkout.entity.CheckoutEntity;
import one.digitalinnovation.ecommerce.checkout.repository.CheckoutRepository;
import one.digitalinnovation.ecommerce.checkout.streaming.PaymentPaidSink;
import one.digitalinnovation.payment.event.PaymentCreatedEvent;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class PaymentPaidListener {

    private CheckoutRepository checkoutRepository;

    @StreamListener(PaymentPaidSink.INPUT)
    public void handler(PaymentCreatedEvent event) {
        CheckoutEntity checkoutEntity = checkoutRepository.findByCode(event.getCheckoutCode().toString()).orElseThrow();
        checkoutEntity.setStatus(CheckoutEntity.Status.APPROVED);
        checkoutRepository.save(checkoutEntity);
    }
}

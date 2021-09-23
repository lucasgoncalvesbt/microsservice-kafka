package one.digitalinnovation.ecommerce.payment.listener;

import lombok.RequiredArgsConstructor;
import one.digitalinnovation.checkout.event.CheckoutCreatedEvent;
import one.digitalinnovation.ecommerce.payment.streaming.CheckoutProcessor;
import one.digitalinnovation.payment.event.PaymentCreatedEvent;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CheckoutCreatedListener {

    private final CheckoutProcessor checkoutProcessor;

    @StreamListener(CheckoutProcessor.INPUT)
    private void handler(CheckoutCreatedEvent event) {
        // Processa pagamento
        // Salvar dados do pagamento
        // Enviar o evento de pagamento processado

        PaymentCreatedEvent paymentCreatedEvent = PaymentCreatedEvent.newBuilder()
                .setCheckoutCode(event.getCheckoutCode())
                .setCheckoutStatus(event.getStatus())
                .setPaymentCode(UUID.randomUUID().toString())
                .build();
        checkoutProcessor.output().send(MessageBuilder.withPayload(paymentCreatedEvent).build());
    }

}

package com.example.skillfactory.service;

import com.example.skillfactory.model.Payment;
import com.example.skillfactory.model.Booking;
import com.example.skillfactory.repository.PaymentRepository;
import com.example.skillfactory.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private BookingRepository bookingRepository;

    /**
     * Получить все платежи
     */
    public List<Payment> findAll() {
        return paymentRepository.findAll();
    }

    /**
     * Найти платеж по ID
     */
    public Payment findById(Integer id) {
        return paymentRepository.findById(id).orElse(null);
    }

    /**
     * Создать новый платеж
     */
    @Transactional
    public Payment createPayment(Payment payment) {
        // Проверка обязательных полей
        if (payment.getPaymentAmount() == null || payment.getPaymentAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Amount must be greater than zero");
        }
        if (payment.getBooking() == null || payment.getPaymentDate() == null) {
            throw new IllegalArgumentException("Booking and payment date must be provided");
        }

        // Убедиться, что связанное бронирование существует
        Optional<Booking> bookingOpt = bookingRepository.findById(payment.getBooking().getId());
        if (!bookingOpt.isPresent()) {
            throw new IllegalArgumentException("Booking with ID " + payment.getBooking().getId() + " not found");
        }

        // Устанавливаем связанное бронирование
        payment.setBooking(bookingOpt.get());

        // Логика для установки статуса или других данных
        payment.setPaymentStatus("PENDING"); // По умолчанию статус "Ожидание"

        return paymentRepository.save(payment);
    }

    @Transactional
    public Payment save(Payment payment) {
        if (payment.getPaymentId() != null) { // Предполагаем, что paymentId — это идентификатор
            Optional<Payment> existingPayment = paymentRepository.findById(payment.getPaymentId());
            if (existingPayment.isPresent()) {
                // Если платеж существует, обновляем его
                return updatePayment(payment.getPaymentId(), payment);
            }
        }
        // Если paymentId == null или платеж не найден, создаём новый
        return createPayment(payment);
    }

    /**
     * Обновить существующий платеж
     */
    @Transactional
    public Payment updatePayment(Integer paymentId, Payment updatedPayment) {
        Optional<Payment> existingPaymentOpt = paymentRepository.findById(paymentId);
        if (!existingPaymentOpt.isPresent()) {
            throw new IllegalArgumentException("Payment with ID " + paymentId + " not found");
        }

        Payment existingPayment = existingPaymentOpt.get();

        // Обновляем данные платежа
        existingPayment.setPaymentAmount(updatedPayment.getPaymentAmount());
        existingPayment.setPaymentDate(updatedPayment.getPaymentDate());
        existingPayment.setPaymentStatus(updatedPayment.getPaymentStatus());
        if (updatedPayment.getBooking() != null) {
            Optional<Booking> bookingOpt = bookingRepository.findById(updatedPayment.getBooking().getId());
            if (bookingOpt.isPresent()) {
                existingPayment.setBooking(bookingOpt.get());
            }
        }

        return paymentRepository.save(existingPayment);
    }

    /**
     * Удалить платеж по ID
     */
    @Transactional
    public void deletePayment(Integer paymentId) {
        paymentRepository.deleteById(paymentId);
    }

    /**
     * Установить статус платежа как "Завершённый"
     */
    @Transactional
    public Payment completePayment(Integer paymentId) {
        Optional<Payment> paymentOpt = paymentRepository.findById(paymentId);
        if (!paymentOpt.isPresent()) {
            throw new IllegalArgumentException("Payment with ID " + paymentId + " not found");
        }

        Payment payment = paymentOpt.get();
        payment.setPaymentStatus("COMPLETED");

        return paymentRepository.save(payment);
    }

    /**
     * Логика для расчета суммы платежа (опционально)
     */
    public BigDecimal calculatePaymentAmount(Booking booking) {
        if (booking == null) {
            throw new IllegalArgumentException("Booking must not be null");
        }

        // Здесь можно реализовать логику, основанную на длительности бронирования или других данных
        return booking.getTotalPrice();
    }
}

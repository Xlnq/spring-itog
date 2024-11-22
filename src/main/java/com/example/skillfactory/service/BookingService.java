package com.example.skillfactory.service;

import com.example.skillfactory.model.Booking;
import com.example.skillfactory.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private PropertyService propertyService;

    // Получить все бронирования
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    // Получить бронирование по ID
    public Booking getBookingById(int id) {
        return bookingRepository.findById(id).orElse(null);
    }

    // Создание нового бронирования
    @Transactional
    public Booking createBooking(Booking booking) {
        if (booking.getUser() == null || booking.getProperty() == null ||
                booking.getStartDate() == null || booking.getEndDate() == null) {
            throw new IllegalArgumentException("All fields must be filled");
        }

        // Дополнительная логика, если необходима (например, расчет общей стоимости)
        if (booking.getTotalPrice() == null || booking.getTotalPrice().compareTo(BigDecimal.ZERO) == 0) {
            // Логика для расчета цены
            booking.setTotalPrice(calculateTotalPrice(booking));
        }

        return bookingRepository.save(booking);
    }

    // Обновление существующего бронирования
    @Transactional
    public Booking updateBooking(int bookingId, Booking updatedBooking) {
        // Ищем существующее бронирование
        Optional<Booking> existingBookingOpt = bookingRepository.findById(bookingId);
        if (!existingBookingOpt.isPresent()) {
            throw new IllegalArgumentException("Booking with ID " + bookingId + " not found");
        }

        Booking existingBooking = existingBookingOpt.get();

        // Обновляем поля бронирования
        existingBooking.setUser(updatedBooking.getUser());
        existingBooking.setProperty(updatedBooking.getProperty());
        existingBooking.setStartDate(updatedBooking.getStartDate());
        existingBooking.setEndDate(updatedBooking.getEndDate());
        existingBooking.setTotalPrice(updatedBooking.getTotalPrice());
        existingBooking.setStatus(updatedBooking.getStatus());

        // Возвращаем обновлённое бронирование
        return bookingRepository.save(existingBooking);
    }

    // Удалить бронирование
    @Transactional
    public void deleteBooking(int bookingId) {
        bookingRepository.deleteById(bookingId);
    }

    // Пример метода для расчета общей стоимости
    private BigDecimal calculateTotalPrice(Booking booking) {
        // Здесь будет логика для расчета стоимости (например, по дням или другим параметрам)
        long days = java.time.temporal.ChronoUnit.DAYS.between(booking.getStartDate(), booking.getEndDate());
        BigDecimal pricePerDay = BigDecimal.valueOf(100.0); // Цена за день как BigDecimal
        return pricePerDay.multiply(BigDecimal.valueOf(days)); // Множим цену на количество дней
    }
}

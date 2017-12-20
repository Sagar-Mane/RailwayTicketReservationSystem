package com.cmpe275.controller;

import com.cmpe275.domain.Transaction;
import com.cmpe275.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.Locale;


/**
 * @author arunabh.shrivastava
 */
@RestController
public class TransactionController {

    private final
    TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }


    @RequestMapping(value = "/api/transaction")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<?> makeTransaction(@RequestParam(value = "userId") Long userId,
                                             @RequestBody Transaction transaction){

        LocalTime now = LocalTime.now();
        LocalTime startTime = LocalTime.parse(transaction.getTickets().get(0).getTrain().getDepartureTime());
        LocalTime timeLeft =startTime.minusSeconds(now.toSecondOfDay());

        if(timeLeft.toSecondOfDay() > 300) {
            Transaction transaction1 = transactionService.makeTransaction(userId , transaction);
            return new ResponseEntity<>(transaction1, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<Object>("Cannot book train starting within 5 minutes", HttpStatus.BAD_REQUEST);
        }

    }

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(value = "/api/getTransaction")
    public ResponseEntity<?> getTransaction(@RequestParam(value = "userId") Long userId) {

        List<Transaction> transactions = transactionService.getTransactions(userId);

        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }


    @RequestMapping(value = "/api/deleteTransaction")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<?> deleteTransaction(@RequestParam(value = "transactionId") Long transactionId,
                                               @RequestParam(value = "userId") Long userId) {

        Transaction transaction2 = transactionService.deleteTransaction(userId, transactionId);
        return new ResponseEntity<>(transaction2, HttpStatus.OK);
    }
}

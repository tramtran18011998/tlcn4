package com.example.demo.serviceimpl;

import com.example.demo.model.Receipt;
import com.example.demo.repository.ReceiptRepository;
import com.example.demo.service.ReceiptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReceiptServiceImpl implements ReceiptService {

    ReceiptRepository receiptRepository;

    @Autowired
    public ReceiptServiceImpl(ReceiptRepository receiptRepository){
        this.receiptRepository=receiptRepository;
    }
    @Override
    public List<Receipt> findAllReceipt() {
        return receiptRepository.findAll();
    }

    @Override
    public Optional<Receipt> findReceiptById(long id) {
        return receiptRepository.findById(id);
    }

    @Override
    public void save(Receipt receipt) {
        receiptRepository.save(receipt);
    }

    @Override
    public void delete(Receipt receipt) {
        receiptRepository.delete(receipt);
    }
}

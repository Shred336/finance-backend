package receipts;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/receipt")
public class ReceiptController {

	@Autowired
	ReceiptRepository receiptRepository;

	@CrossOrigin
	@GetMapping()
	public List<Receipt> getReceipts() {
		return receiptRepository.findAll();
	}

	@GetMapping("/{id}")
	public Optional<Receipt> getReceipt(@PathVariable Long id) {
		return receiptRepository.findById(id);
	}

	@PostMapping()
	public Receipt addReceipt(@RequestBody Receipt receipt) {
		return receiptRepository.save(receipt);
	}

	@CrossOrigin
	@DeleteMapping("/{id}")
	public void deleteReceipt(@PathVariable Long id) {
		receiptRepository.deleteById(id);
	}

	@CrossOrigin
	@PutMapping("/{id}")
	public Receipt updateReceipt(@PathVariable Long id, @RequestBody Receipt receipt) {
		Receipt foundReceipt = receiptRepository.findById(id).orElse(null);
		if (foundReceipt != null) {
			
		
			foundReceipt.setStore(receipt.getStore());
			foundReceipt.setCategory(receipt.getCategory());
			foundReceipt.setTotal(receipt.getTotal());
			foundReceipt.setPaymentMethod(receipt.getPaymentMethod());
			foundReceipt.setPaidOnDate(receipt.getPaidOnDate());
			

			receiptRepository.save(foundReceipt);
			return foundReceipt;
		}
		return null;
	}
}
package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;
import lk.ijse.dto.BorrowedDetailsDto;

import java.util.List;

public interface BorrowedBookBO extends SuperBO {
    boolean saveBorrowedDetail(BorrowedDetailsDto dto);

    List<BorrowedDetailsDto> getAllBorrowed();

    List<BorrowedDetailsDto> getAllBorrowedFromUser(String userName);
}

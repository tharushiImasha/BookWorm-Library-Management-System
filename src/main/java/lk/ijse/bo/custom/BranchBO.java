package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;
import lk.ijse.dto.BranchDto;
import lk.ijse.entity.Branch;
import lk.ijse.entity.User;

import java.util.List;

public interface BranchBO extends SuperBO {
    boolean saveBranch(BranchDto branchDto);

    boolean updateBranch(BranchDto branchDto);

    boolean deleteBranch(BranchDto branchDto);

    Branch searchBranch(String id);

    List<BranchDto> getAllBranch();
}

package lk.ijse.entity;

import jakarta.persistence.*;
import lk.ijse.dto.BranchDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "branch")
public class Branch {

    @Id
    @Column(name = "branch_id")
    private String id;

    @Column
    private String location;

    @ManyToOne
    @JoinColumn(name = "user_name")
    private User user;

    public BranchDto toDto(){
        BranchDto branchDto = new BranchDto();
        branchDto.setId(this.id);
        branchDto.setLocation(this.location);
        //branchDto.getUserName(this.user);

        return branchDto;
    }
}

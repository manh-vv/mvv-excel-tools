package mzonex.excel.tools.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
@Getter
@Setter
public class Customer implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private Integer id;

    @Column(name = "account_number")
    private String accountNumber;

    @Column(name = "account_owner")
    private String accountOwner;

    private String bank;

    @Column(name = "bank_branch")
    private String bankBranch;

    @Column(name = "bank_name")
    private String bankName;

    @Column(name = "code_kh")
    private String codeKh;

    private String customername;

    private String location;

    @Column(name = "t_cktm_12_17")
    private Long tCktm1217;

    @Column(name = "t_nam_1")
    private Long tNam1;

    @Column(name = "t_q_4")
    private Long tQ4;

    @Column(name = "t_sum")
    private Long tSum;

    @Column(name = "t_tb_12_17")
    private Long tTb1217;

    @Column(name = "t_xe")
    private Long tXe;
}

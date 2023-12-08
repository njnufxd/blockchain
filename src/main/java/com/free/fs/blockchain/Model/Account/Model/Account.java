package com.free.fs.blockchain.Model.Account.Model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@TableName("sys_account")
@EqualsAndHashCode(callSuper = true)
public class Account extends Model<Account> {
    private Long userId;
    private String address;
    private String privatekey;
    private String publickey;
    private String filename;
}

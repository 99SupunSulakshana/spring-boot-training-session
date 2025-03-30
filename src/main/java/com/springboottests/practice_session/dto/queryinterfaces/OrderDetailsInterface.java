package com.springboottests.practice_session.dto.queryinterfaces;

import java.util.ArrayList;
import java.util.Date;

public interface OrderDetailsInterface {

    String getCustomerName();

    String getCustomerAddress();

    String getContactNumber();

    Date getDate();

    Double getTotal();

}

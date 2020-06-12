/*
 * www.qwfys.com Inc.
 * Copyright (c) 2004- 2020 All Rights Reserved.
 */

package pl.michalperlak.querydsl.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pl.michalperlak.querydsl.bo.SubscriptionProjectionBo;
import pl.michalperlak.querydsl.bo.UserProjectionBo;

import java.util.List;

public interface UserService {

    Page<UserProjectionBo> getFirstNameLikePage(String firstNameLike, Pageable pageable);

    List<SubscriptionProjectionBo> getAllSubscriptions();

    void save();
}

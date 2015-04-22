package com.asc.bdhub.service.account;

import org.junit.Before;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.yjy.bsq.repository.account.UserInfoDao;
import com.yjy.bsq.service.account.AccountService;

/**
 * AccountService的测试用例, 测试Service层的业务逻辑.
 * 
 * @author calvin
 */
public class AccountServiceTest {

	@InjectMocks
	private AccountService accountService;

	@Mock
	private UserInfoDao mockUserDao;


	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
//		ShiroTestUtils.mockSubject(new ShiroUser(3, "foo", "Foo"));
	}

	

}

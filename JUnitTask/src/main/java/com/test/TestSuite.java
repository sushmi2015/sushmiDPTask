package com.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.test.EmailValidatorTest;
import com.test.PasswordValidatorTest;

@RunWith(Suite.class)
@SuiteClasses({ EmailValidatorTest.class, PasswordValidatorTest.class })
public class TestSuite {

}

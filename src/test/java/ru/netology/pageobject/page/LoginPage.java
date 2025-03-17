package ru.netology.pageobject.page;

import ru.netology.pageobject.data.DataGenerator;

import static com.codeborne.selenide.Selenide.$;

public class LoginPage {


    public VerificationPage validLogin(DataGenerator.AuthInfo info){
        $("[data-test-id='login'] input.input__control").setValue(info.getLogin());
        $("[data-test-id='password'] input.input__control").setValue(info.getPassword());
        $("[data-test-id='action-login']").click();
        return new VerificationPage();
    }
}

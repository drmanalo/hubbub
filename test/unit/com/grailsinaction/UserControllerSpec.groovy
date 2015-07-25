package com.grailsinaction

import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.Specification
import spock.lang.Unroll

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(UserController)
@Mock([User, Profile])
class UserControllerSpec extends Specification {

    def "Registering a user with known good parameters"() {

        String homepage = "http://blogs.bytecode.com.au/tim"

        given: "a set of user parameters"
        params.with {
            loginId = "timmy_manalo"
            password = "winnning"
            homepage = homepage
        }

        and: "a set of profile parameters"
        params["profile.fullName"] = "Timothy Manalo"
        params["profile.email"] = "timmy@bytecode.com.au"
        params["profile.homepage"] = homepage

        when: "the user is registered"
        request.method = "POST"
        controller.register()

        then: "the user is created, and browser redirected"
        response.redirectedUrl == "/"
        User.count() == 1
        Profile.count() == 1
    }

    @Unroll
    def "Registration command object for #loginId validate correctly"() {

        given: "a mocked command object"
        def urc = mockCommandObject(UserRegistrationCommand)

        and: "a set of initial values from the spock test"
        urc.loginId = loginId
        urc.password = password
        urc.passwordRepeat = passwordRepeat
        urc.fullName = "Your Name"
        urc.email = "someone@nowhere.net"

        when: "the validator is invoked"
        def isValidRegistration = urc.validate()

        then: "the appropriate fields are flagged as errors"
        isValidRegistration == anticipatedValid
        urc.errors.getFieldError(fieldInError)?.code == errorCode

        where:
        loginId   | password   | passwordRepeat| anticipatedValid   | fieldInError       | errorCode
        "sophia"  | "password" | "no-match"    | false              | "passwordRepeat"   | "validator.invalid"
        "timothy" | "password" | "password"    | true               | null               | null
        "ava"     | "password" | "password"    | false              | "loginId"          | "size.toosmall"

    }
}

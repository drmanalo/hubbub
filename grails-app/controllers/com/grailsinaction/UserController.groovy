package com.grailsinaction

class UserController {

    static scaffold = true

    def register() {
        if (request.method == "POST") {
            def user = new User(params)
            if (user.validate()) {
                user.save()
                flash.message = "Successfully added a user"
                redirect(uri: "/")
            } else {
                flash.message = "Error registering user..."
                return [user: user]
            }
        }
    }

}

class UserRegistrationCommand {

    String loginId
    String password
    String passwordRepeat
    byte[] photo
    String fullName
    String bio
    String homepage
    String email
    String timezone
    String country
    String jabberAddress

    static constraints = {
        importFrom Profile
        importFrom User
        password(size: 6..8, blank: false, validator: { passwd, urc -> return passwd != urc.loginId })
        passwordRepeat(nullable: false, validator: { passwd2, urc -> return passwd2 == urc.password })
    }

}
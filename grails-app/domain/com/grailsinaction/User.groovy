package com.grailsinaction

class User {

    String loginId
    String password
    Date dateCreated

    static constraints = {
        loginId size: 4..20, unique: true, nullable: false
        password size: 6..20, nullable: false, validator: { 
        	passwd, user -> passwd != user.loginId
        }
        profile nullable: true
    }
    static hasOne = [ profile : Profile ]
    static hasMany = [ posts : Post, tags: Tag, following: User ]
}

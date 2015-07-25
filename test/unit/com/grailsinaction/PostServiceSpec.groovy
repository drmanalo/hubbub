package com.grailsinaction

import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(PostService)
@Mock([User, Post])
class PostServiceSpec extends Specification {

    def "Valid posts get saved and added to the user"() {

        String loginId = "chuck_norris"
        String password = "password"
        String content = "First post!"

        given: "A new user in the db"
        new User(loginId: loginId, password: password).save(failOnError: true)

        when: "a new post is created by the service"
        def post = service.createPost(loginId, content)

        then: "the post returned and added to the user"
        post.content == content
        User.findByLoginId(loginId).posts.size() == 1
    }

    def "Invalid posts generate exceptional outcomes"() {

        String loginId = "chuck_norris"
        String password = "password"

        given: "A new user in the db"
        new User(loginId: loginId, password: password).save(failOnError: true)

        when: "an invalid post is attempted"
        def post = service.createPost(loginId, null)

        then: "an exception is thrown and no post is saved"
        thrown(PostException)
    }

    void "test something"() {
    }
}

package grails.sample.app

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(HelloController)
class HelloControllerSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    def "render 'Hello world!'"() {
        when:
        controller.index()

        then:
        response.text == 'Hello world!'
    }
}

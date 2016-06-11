package grails.sample.app

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Todo)
class TodoSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    def "date and description are not blank"() {
        when:
        def test = new Todo(date: '2016/05/05', description: 'test')

        then:
        test.validate()
    }

    def "date and descriptopn are blank"() {
        setup:
        def test = new Todo(date: date, description: description)

        expect:
        !test.validate()

        where:
        date | description
        ''   | ''
        null | null
    }

    def "date is blank"() {
        setup:
        def test = new Todo(date: date, description: description)

        expect:
        !test.validate()

        where:
        date | description
        ''   | 'test'
        null | 'test'
    }

    def "description is blank"() {
        setup:
        def test = new Todo(date: date, description: description)

        expect:
        !test.validate()

        where:
        date       | description
        '2016/1/1' | ''
        '2016/1/1' | null
    }
}

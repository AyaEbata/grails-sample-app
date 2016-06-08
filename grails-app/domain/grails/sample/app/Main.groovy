package grails.sample.app

class Main {

    Date date
    String description

    static constraints = {
        date        blank: false
        description blank: false, maxSize: 20
    }
}

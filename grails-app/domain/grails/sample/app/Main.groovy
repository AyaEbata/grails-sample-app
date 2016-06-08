package grails.sample.app

class Main {

    String date
    String description

    static constraints = {
        date        blank: false
        description blank: false, maxSize: 20
    }
}

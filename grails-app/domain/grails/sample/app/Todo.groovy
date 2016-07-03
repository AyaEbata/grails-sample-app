package grails.sample.app

class Todo {

    String date
    String description

    static constraints = {
        date        blank: false
        description blank: false, maxSize: 20
    }
}

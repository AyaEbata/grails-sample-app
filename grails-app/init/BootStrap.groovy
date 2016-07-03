import grails.sample.app.Todo
import grails.util.Environment

class BootStrap {

    def init = { servletContext ->
        Environment.executeForCurrentEnvironment {
            development {
                new Todo(date: "2016/06/09", description: "めも１").save()
                new Todo(date: "2016/06/10", description: "めも２").save()
            }
        }
    }
    def destroy = {
    }
}

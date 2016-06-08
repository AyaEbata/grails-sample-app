package grails.sample.app

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class MainController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Main.list(params), model:[mainCount: Main.count()]
    }

    def show(Main main) {
        respond main
    }

    def create() {
        respond new Main(params)
    }

    @Transactional
    def save(Main main) {
        if (main == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (main.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond main.errors, view:'create'
            return
        }

        main.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'main.label', default: 'Main'), main.id])
                redirect main
            }
            '*' { respond main, [status: CREATED] }
        }
    }

    def edit(Main main) {
        respond main
    }

    @Transactional
    def update(Main main) {
        if (main == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (main.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond main.errors, view:'edit'
            return
        }

        main.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'main.label', default: 'Main'), main.id])
                redirect main
            }
            '*'{ respond main, [status: OK] }
        }
    }

    @Transactional
    def delete(Main main) {

        if (main == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        main.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'main.label', default: 'Main'), main.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'main.label', default: 'Main'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}

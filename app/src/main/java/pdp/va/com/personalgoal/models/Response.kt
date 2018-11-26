package pdp.va.com.personalgoal.models

/**
 * Response holder provided to the UI
 */
class Response<D> private constructor(val status: Status, val data: D?, val error: Throwable?) {
    companion object {

        fun <D> loading(): Response<D> {
            return Response(Status.LOADING, null, null)
        }

        fun <D> success(data: D): Response<D> {
            return Response(Status.SUCCESS, data, null)
        }

        fun <D> error(error: Throwable): Response<D> {
            return Response(Status.ERROR, null, error)
        }
    }
}
// 代码生成时间: 2025-09-13 18:39:39
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

// PaymentService 类，使用 JERSEY 框架创建 REST API
@Path("/payment")
public class PaymentService {

    // 初始化方法，模拟数据库或其他存储的支付信息
    private static final Map<String, Double> payments = new HashMap<>();

    // POST 方法，创建支付记录
    @POST
    @Path("/create/{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response createPayment(@PathParam("userId") String userId, Double amount) {
        try {
            // 检查支付金额是否为正数
            if (amount <= 0) {
                return Response.status(Response.Status.BAD_REQUEST).entity(
                        "Payment amount must be greater than zero.").build();
            }
            // 存储支付信息
            payments.put(userId, amount);
            return Response.status(Response.Status.CREATED).entity(
                    "Payment for user 
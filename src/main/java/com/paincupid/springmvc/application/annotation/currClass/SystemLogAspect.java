package com.paincupid.springmvc.application.annotation.currClass;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.Map;
 
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
 
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
 
import com.paincupid.springmvc.application.annotation.currClass.BusUserlogModel;

 
@Aspect
@Component
public  class SystemLogAspect {
    //注入Service用于把日志保存数据库    
    /*@Resource   
    private BusUserlogService busUserlogService;*/
    //本地异常日志记录对象    
    private  static  final Logger logger = LoggerFactory.getLogger(SystemLogAspect. class);
     
    //Controller层切点
    @Pointcut("@annotation(com.dlodlo.util.OpLogger)")
    public  void controllerAspect() {
    }    
     
    /**  
     * 前置通知 用于拦截Controller层记录用户的操作 
     *
     * @param joinPoint 切点
     */
    @AfterReturning(pointcut="controllerAspect()")
    public  void doBefore(JoinPoint joinPoint) {
        handleLog(joinPoint,null);
    }
     
     
//    @AfterReturning(pointcut="controllerAspect()",argNames = "joinPoint,retVal",returning = "retVal")
    @AfterThrowing(value="controllerAspect()",throwing="e")
    public void doAfter(JoinPoint joinPoint,Exception e)
    {
        handleLog(joinPoint,e);
    }
 
private void handleLog(JoinPoint joinPoint,Exception e) {
    try {
        //获得注解
        OpLogger controllerLog = giveController(joinPoint);
        if(controllerLog == null)
        {
            return;
        }
        //Mock获取当前的用户
        String userId = "123456879";
        String userName = "lee";
        String url = "www.google.com";
        HttpServletRequest  request = null;
        //*========数据库日志=========*//    
        BusUserlogModel userlogModel = new BusUserlogModel();
        //请求的IP
        String ip = "192.168.0.1"; //get from request
        userlogModel.setUserIp(ip);
        userlogModel.setCreateTIme(new Date());
        userlogModel.setUrl(url);

        userlogModel.setUserId(userId);
        userlogModel.setUserName(userName);
         
        if(e != null)
            userlogModel.setErrorMessage(e.getMessage());
         
        //处理设置注解上的参数
        getControllerMethodDescription(controllerLog,userlogModel,request);
        //保存数据库
        //busUserlogService.insert(userlogModel);
        System.out.println(userlogModel.getUserId()+", "+userlogModel.getUserName());
      }  catch (Exception exp) {
       //记录本地异常日志
       logger.error("==前置通知异常==");
       logger.error("异常信息:{}", exp.getMessage());
       exp.printStackTrace();
      }
}
     
    /**  
     * 获取注解中对方法的描述信息 用于Controller层注解
     *  
     * @param joinPoint 切点
     * @return 方法描述
     * @throws Exception
     */   
     public  static void getControllerMethodDescription(OpLogger controllerLog,BusUserlogModel userlogModel,HttpServletRequest request)  throws Exception { 
        //设置action动作
        /*userlogModel.setAction(controllerLog.action());
        //设置标题
        userlogModel.setTitle(controllerLog.title());
        //设置channel
        userlogModel.setChannel(controllerLog.channel());
        //是否需要保存request，参数和值
        if(controllerLog.isSaveRequestData())
        {
            //获取参数的信息，传入到数据库中。
            setRequestValue(userlogModel,request);
        }*/
     }
 
     /**
      * 获取请求的参数，放到log中
      * @param userlogModel
      * @param request
      */
    @SuppressWarnings("rawtypes")
    private static void setRequestValue(BusUserlogModel userlogModel,HttpServletRequest request) {
        if(userlogModel == null)
            userlogModel = new BusUserlogModel();
        Map map = request.getParameterMap();
        String params = map.toString();
        userlogModel.setRequestParam(params);
    }
     
    /**
     * 是否存在注解，如果存在就记录日志
     * @param joinPoint
     * @param controllerLog
     * @return
     * @throws Exception
     */
    private static OpLogger giveController(JoinPoint joinPoint) throws Exception
    {
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;  
        Method method = methodSignature.getMethod();  
         
        if(method != null)
        {
            return method.getAnnotation(OpLogger.class);
        }
        return null;
    }
}
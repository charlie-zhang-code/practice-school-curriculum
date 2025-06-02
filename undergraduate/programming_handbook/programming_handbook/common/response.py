from rest_framework.response import Response


class APIResponse(Response):
    def __init__(self, data=None, code=200, message='success', success=True, status=None,
                 template_name=None, headers=None, exception=False, content_type=None):
        """
        统一API响应格式
        :param data: 返回数据
        :param code: 状态码
        :param message: 提示信息
        :param success: 是否成功
        :param status: HTTP状态码
        :param template_name: 模板名称
        :param headers: 响应头
        :param exception: 是否异常
        :param content_type: 内容类型
        """
        super().__init__(None, status=status)

        self.data = {
            "code": code,
            "data": data,
            "message": message,
            "success": success
        }

        self.template_name = template_name
        self.exception = exception
        self.content_type = content_type

        if headers:
            for name, value in headers.items():
                self[name] = value
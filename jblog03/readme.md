# jBlog03

## 설정

* web.xml
	* listener : ContextLoadListener
		* applicationContext.xml
	* filter : encoding filter
	* servlet : DispatcherServlet
* pom.xml
	* spring ( JCL 제외 : for log )
	* spring web, mvc
	* logBack : for log
	* validation
	* jstl
* spring-servlet.xml
	* scan : controller, exception(log)
	* viewResolver 설정 (prefix,suffix)
	* defaultServlet handler
	* Message source : validation 통과하지 못할 경우 message

* applicationContext.xml
	* 
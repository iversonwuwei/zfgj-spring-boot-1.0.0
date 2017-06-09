<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<div class="foot_link container"> 
	<c:forEach items="${fns:getAd('footer','')}" var="ad">
		<c:forEach items="${ad.medias}" var="media" varStatus="status">
			<c:if test="${status.index ne 0}">|</c:if>
			<a href="${media.link }" target="_blank">${media.title }</a>
		</c:forEach>
	</c:forEach>
	<c:if test="${fnc:getSiteFoot('1') != null}">
        <p>
            ${fnc:getSiteFoot('1').description}
        </p>
            ${fnc:getSiteFoot('1').copyright}
    </c:if>
</div>

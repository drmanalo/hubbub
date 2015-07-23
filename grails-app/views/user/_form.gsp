<%@ page import="com.grailsinaction.User" %>



<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'loginId', 'error')} required">
	<label for="loginId">
		<g:message code="user.loginId.label" default="Login Id" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="loginId" maxlength="20" required="" value="${userInstance?.loginId}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'password', 'error')} required">
	<label for="password">
		<g:message code="user.password.label" default="Password" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="password" maxlength="20" required="" value="${userInstance?.password}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'profile', 'error')} ">
	<label for="profile">
		<g:message code="user.profile.label" default="Profile" />
		
	</label>
	<g:select id="profile" name="profile.id" from="${com.grailsinaction.Profile.list()}" optionKey="id" optionValue="displayString" value="${userInstance?.profile?.id}" class="many-to-one" noSelection="['null': '']"/>

</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'following', 'error')} ">
	<label for="following">
		<g:message code="user.following.label" default="Following" />
		
	</label>
	<g:select name="following" from="${com.grailsinaction.User.list()}" multiple="multiple" optionKey="id" size="5" value="${userInstance?.following*.id}" class="many-to-many"/>

</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'posts', 'error')} ">
	<label for="posts">
		<g:message code="user.posts.label" default="Posts" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${userInstance?.posts?}" var="p">
    <li><g:link controller="post" action="show" id="${p.id}">${p?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="post" action="create" params="['user.id': userInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'post.label', default: 'Post')])}</g:link>
</li>
</ul>


</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'tags', 'error')} ">
	<label for="tags">
		<g:message code="user.tags.label" default="Tags" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${userInstance?.tags?}" var="t">
    <li><g:link controller="tag" action="show" id="${t.id}">${t?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="tag" action="create" params="['user.id': userInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'tag.label', default: 'Tag')])}</g:link>
</li>
</ul>


</div>


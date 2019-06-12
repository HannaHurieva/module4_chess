<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>

<@c.page>

  <div>
  <@l.logout />
  </div>

  <div>Список пользователей</div>
  <#list users as user>
    <div>
    <b>${user.id}</b>
    <span>${user.username}</span>
    </div>
  <#else>
    No message
  </#list>

</@c.page>
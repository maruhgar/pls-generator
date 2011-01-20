<#import "spring.ftl" as spring />

<div class="heading">

    <form method="post" action="<@spring.url '/theme'/>">
        <div class="theme">
            <select name="theme">
                <option value="default"><@spring.message 'pls.theme.default'/></option>
                <option value="black"><@spring.message 'pls.theme.black'/></option>
            </select>
            <input type="submit" name="submit" value="<@spring.message 'pls.theme.change'/>"/>
        </div>
    </form>

    <div class="header-title">
        <header>
            <@spring.message 'pls.header'/>
        </header>
    </div>
</div>

<div class="menu">
    <nav>
        <ul>
            <li><a href="<@spring.url '/browse'/>"><@spring.message 'pls.menu.home'/></a></li>
            <li><a href="<@spring.url '/admin/configure'/>"><@spring.message 'pls.menu.configure'/></a></li>
            <li><a href="<@spring.url '/admin/profile'/>"><@spring.message 'pls.menu.profile'/></a></li>
        </ul>
    </nav>
</div>

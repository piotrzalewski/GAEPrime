
get "/", forward: "/WEB-INF/pages/index.gtpl"
get "/datetime", forward: "/datetime.groovy"
get "/prime/@number?", forward: "/prime.groovy"
get "/table/@number?", forward: "/table.groovy"
get "/favicon.ico", redirect: "/images/gaelyk-small-favicon.png"
all "/_ah/start", forward: "/start.groovy"

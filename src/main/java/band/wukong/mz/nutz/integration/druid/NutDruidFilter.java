package band.wukong.mz.nutz.integration.druid;

import org.nutz.mvc.NutFilter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * As you see...
 *
 * @author wukong(wukonggg@139.com)
 */
public class NutDruidFilter extends NutFilter {

    protected Set<String> prefixs = new HashSet<String>();


    public void init(FilterConfig conf) throws ServletException {
        super.init(conf);
        prefixs.add(conf.getServletContext().getContextPath() + "/druid/");
        prefixs.add(conf.getServletContext().getContextPath() + "/rs/");
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        if (req instanceof HttpServletRequest) {
            String uri = ((HttpServletRequest) req).getRequestURI();
            for (String prefix : prefixs) {
                if (uri.startsWith(prefix)) {
                    chain.doFilter(req, resp);
                    return;
                }
            }
        }
        super.doFilter(req, resp, chain);
    }
}

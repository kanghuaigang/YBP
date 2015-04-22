package org.apache.jsp.tag.web;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class pagination_tag
    extends javax.servlet.jsp.tagext.SimpleTagSupport
    implements org.apache.jasper.runtime.JspSourceDependent {


  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List _jspx_dependants;

  private JspContext jspContext;
  private java.io.Writer _jspx_sout;
  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.AnnotationProcessor _jsp_annotationprocessor;

  public void setJspContext(JspContext ctx) {
    super.setJspContext(ctx);
    java.util.ArrayList _jspx_nested = null;
    java.util.ArrayList _jspx_at_begin = null;
    java.util.ArrayList _jspx_at_end = null;
    this.jspContext = new org.apache.jasper.runtime.JspContextWrapper(ctx, _jspx_nested, _jspx_at_begin, _jspx_at_end, null);
  }

  public JspContext getJspContext() {
    return this.jspContext;
  }

  public Object getDependants() {
    return _jspx_dependants;
  }

  private void _jspInit(ServletConfig config) {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(config.getServletContext()).getExpressionFactory();
    _jsp_annotationprocessor = (org.apache.AnnotationProcessor) config.getServletContext().getAttribute(org.apache.AnnotationProcessor.class.getName());
  }

  public void _jspDestroy() {
  }

  public void doTag() throws JspException, java.io.IOException {
    PageContext _jspx_page_context = (PageContext)jspContext;
    HttpServletRequest request = (HttpServletRequest) _jspx_page_context.getRequest();
    HttpServletResponse response = (HttpServletResponse) _jspx_page_context.getResponse();
    HttpSession session = _jspx_page_context.getSession();
    ServletContext application = _jspx_page_context.getServletContext();
    ServletConfig config = _jspx_page_context.getServletConfig();
    JspWriter out = jspContext.getOut();
    _jspInit(config);
    jspContext.getELContext().putContext(JspContext.class,jspContext);

    try {
      out.write("\n");
      out.write("<script type=\"text/html\" id='pagebar_template'>\n");
      out.write("\t<$var paginationSize = 5;$>\n");
      out.write("\t<div class=\"pagination\">\n");
      out.write("\t<ul>\n");
      out.write("\t\t<$if(firstPage){$>\n");
      out.write("\t\t\t<li class=\"disabled\"><a href=\"#\"> &lt;&lt;</a> </li>\n");
      out.write("       \t\t<li class=\"disabled\"><a href=\"#\"> &lt; </a></li>\n");
      out.write("\t\t<$}else{$>\n");
      out.write("\t\t\t<li  ><a href=\"#\" onclick=\"pageNav(0)\">&lt;&lt; </a></li>\n");
      out.write("\t\t\t<li  ><a href=\"#\" onclick=\"pageNav(<$=number-1$>)\"> &lt;</a></li>\n");
      out.write("\t\t<$}$>\n");
      out.write("\t\t<$var current =  number + 1;$>\n");
      out.write("\t\t<$var begin = Math.max(1, current - Math.floor(paginationSize/2));$>\n");
      out.write("\t\t<$var end = Math.min(begin + paginationSize-1, totalPages);$>\n");
      out.write("\t\t<$if(end-begin<paginationSize-1 && totalPages>=paginationSize){$>\n");
      out.write("\t\t\t<$begin = totalPages-paginationSize+1;$>\n");
      out.write("\t\t\t<$end = totalPages;$>\n");
      out.write("\t\t<$}$>\n");
      out.write("\t\t<$for(var i=begin;i<=end;i++){$>\n");
      out.write("\t\t\t<$if(i==current){$>\n");
      out.write("\t\t\t\t<li class=\"active\"> <a href=\"#\"> <$=i$></a> </li>\t\n");
      out.write("\t\t\t<$}else{$>\n");
      out.write("\t\t\t\t<li  ><a href=\"#\" onclick=\"pageNav(<$=i-1$>)\">  <$=i$> </a></li>\t\n");
      out.write("\t\t\t<$}$>\n");
      out.write("\t\t<$}$>\n");
      out.write("\t\t<$if(lastPage){$>\n");
      out.write("\t\t\t<li class=\"disabled\"> <a href=\"#\"> &gt; </a></li>\n");
      out.write("\t\t\t<li class=\"disabled\"> <a href=\"#\"> &gt;&gt; </a></li>\n");
      out.write("\t\t<$}else{$>\n");
      out.write("\t\t\t<li  > <a href=\"#\" onclick=\"pageNav(<$=number+1$>)\"> &gt; </a></li>\n");
      out.write("\t\t\t<li  > <a href=\"#\" onclick=\"pageNav(<$=totalPages-1$>)\"> &gt;&gt; </a></li>\t\t\n");
      out.write("\t\t<$}$>\n");
      out.write("\t</ul>\n");
      out.write("</div>\n");
      out.write("</script>\n");
      out.write("\n");
    } catch( Throwable t ) {
      if( t instanceof SkipPageException )
          throw (SkipPageException) t;
      if( t instanceof java.io.IOException )
          throw (java.io.IOException) t;
      if( t instanceof IllegalStateException )
          throw (IllegalStateException) t;
      if( t instanceof JspException )
          throw (JspException) t;
      throw new JspException(t);
    } finally {
      jspContext.getELContext().putContext(JspContext.class,super.getJspContext());
      ((org.apache.jasper.runtime.JspContextWrapper) jspContext).syncEndTagFile();
    }
  }
}

<%
  boolean success = (Boolean)session.getAttribute("success");
  if (success)
      out.println("Item updated successfully.");
  else
      out.println("Error: Unable to update item.");
%>
<%@ page import="kz.bitlab.javaee.models.User" %>
<%@ page import="java.sql.Timestamp, java.text.SimpleDateFormat" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="kz.bitlab.javaee.models.News" %>
<%@ page import="kz.bitlab.javaee.models.Comment" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">

<%@include file="common/header.jsp"%>

<body id="page-top">

<% User user = (User) request.getAttribute("user");
    List<Comment> comments = (List<Comment>) request.getAttribute("comments");
    List<News> news = (List<News>) request.getAttribute("news");
    SimpleDateFormat sdf = new SimpleDateFormat("d MMMM yyyy");
%>
<!-- Page Wrapper -->
<div id="wrapper">

    <!-- Content Wrapper -->
    <div id="content-wrapper" class="d-flex flex-column">

        <!-- Main Content -->
        <div id="content">



            <!-- Topbar -->
            <nav class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">

                <!-- Sidebar Toggle (Topbar) -->
                <button id="sidebarToggleTop" class="btn btn-link d-md-none rounded-circle mr-3">
                    <i class="fa fa-bars"></i>
                </button>

                <!-- Topbar Navbar -->
                <ul class="navbar-nav ml-auto">


                    <div class="topbar-divider d-none d-sm-block"></div>

                    <!-- Nav Item - User Information -->
                    <li class="nav-item dropdown no-arrow">
                        <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button"
                           data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            <span class="mr-2 d-none d-lg-inline text-gray-600 small"><%=user.getFirstName()%> <%=user.getLastName()%></span>
                            <img class="img-profile rounded-circle"
                                 src="img/undraw_profile.svg">
                        </a>
                        <!-- Dropdown - User Information -->
                        <div class="dropdown-menu dropdown-menu-right shadow animated--grow-in"
                             aria-labelledby="userDropdown">
                            <a class="dropdown-item" href="#">
                                <i class="fas fa-user fa-sm fa-fw mr-2 text-gray-400"></i>
                                Profile
                            </a>
                            <a class="dropdown-item" href="#">
                                <i class="fas fa-cogs fa-sm fa-fw mr-2 text-gray-400"></i>
                                Settings
                            </a>
                            <div class="dropdown-divider"></div>
                            <a class="dropdown-item" href="#" data-toggle="modal" data-target="#logoutModal">
                                <i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
                                Logout
                            </a>
                        </div>
                    </li>

                </ul>

            </nav>
            <!-- End of Topbar -->

            <!-- Begin Page Content -->
            <div class="container">

                <!-- Page Heading -->
                <div class="d-sm-flex align-items-center justify-content-between mb-4">
                    <h1 class="h3 mb-0 text-gray-800">News</h1>
                </div>

                <%for (News post : news){%>
                    <div class="row ">
                        <div class="card shadow mb-4 col-8">
                            <div class="card-header py-3">
                                <h6 class="m-0 font-weight-bold text-primary"><%=post.getTitle()%></h6>
                                <%
                                String formattedDate = sdf.format(post.getPostDate());
                                %>
                                <p class="mb-0 text-muted" ><%=formattedDate%></p>
                            </div>
                            <div class="card-body">
                                <h5 class="my-4 text-dark"><%=post.getContent()%></h5>
                                <form action="/addComment" method="post">
                                    <input type="hidden" name="postId" value="<%=post.getId()%>">
                                    <textarea class="form-control" type="text" name="comment" placeholder="Type your comment here"></textarea>
                                    <button class="btn btn-primary mt-1" type="submit">Send</button>
                                </form>
                                <% for(Comment comment : comments){
                                    if(comment.getNews().getId() == post.getId()){%>
                                    <div class="row mt-4">
                                        <div class="card mb-3x w-100">
                                            <div class="card-body">
                                                <div class="d-flex justify-content-between align-items-center mb-3">
                                                    <div class="d-flex align-items-center">
                                                        <img class="img-profile rounded-circle" src="img/undraw_profile.svg" style="width:5%">
                                                        <div class="mx-2">
                                                            <p class="card-title mb-0 text-primary"><%=comment.getUser().getFirstName()%> <%=comment.getUser().getLastName()%></p>
                                                            <%String formattedDate1 = sdf.format(comment.getPostDate());%>
                                                            <small class="text-muted"><%=formattedDate1%></small>
                                                        </div>
                                                    </div>
                                                    <% if(comment.getUser().getId() == user.getId()) {%>
                                                    <form action="/deleteComment" method="post">
                                                        <input type="hidden" name="commentId" value="<%=comment.getId()%>">
                                                        <button class="btn btn-sm btn-outline-danger">
                                                            <i class="bi bi-trash"></i>
                                                        </button>
                                                    </form>
                                                    <%}%>
                                                </div>
                                                <p class="card-text"><%=comment.getComment()%></p>
                                            </div>
                                        </div>
                                    </div>
                                <% }
                                } %>

                            </div>
                        </div>
                    </div>
                <%}%>





            <!-- /.container-fluid -->

        </div>
        <!-- End of Main Content -->


    </div>
    <!-- End of Content Wrapper -->

</div>
<!-- End of Page Wrapper -->

<!-- Scroll to Top Button-->
<a class="scroll-to-top rounded" href="#page-top">
    <i class="fas fa-angle-up"></i>
</a>

<!-- Logout Modal-->
<div class="modal fade" id="logoutModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
     aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Ready to Leave?</h5>
                <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">×</span>
                </button>
            </div>
            <div class="modal-body">Select "Logout" below if you are ready to end your current session.</div>
            <div class="modal-footer">
                <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancel</button>
                <a class="btn btn-primary" href="/logout">Logout</a>
            </div>
        </div>
    </div>
</div>

<%@include file="common/footer.jsp"%>
</body>

</html>
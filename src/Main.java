import spark.ModelAndView;
import spark.Session;
import spark.Spark;
import spark.template.mustache.MustacheTemplateEngine;

import java.util.ArrayList;
import java.util.HashMap;

public class Main {
    static User user;
    private static HashMap m = new HashMap();
    private static HashMap<String, User> users = new HashMap<>();
    private static ArrayList<Job> jobs = new ArrayList<>();
    private static int newId;
    private static int listId = 0;
    private static int currentId;
    private static String show = "";

    public static void main(String[] args) {
        Spark.init();

        Spark.get("/", ((request, response) -> {

                    Session session = request.session();
                    String userName = session.attribute("userName");
                    User user = users.get(userName);



                    if (user == null) {
                        return new ModelAndView(m, "/login.html");
                    }
                    m.put("user", user);
                    for (Job job : jobs) {
                        if (user.getId() != job.getId()) {
                            job.setShow(null);
                        }else {
                            job.setShow("true");
                        }
                    }
                    m.put("show", show);
                    m.put("jobs", jobs);


                    return new ModelAndView(m, "/job.html");
                }),
                new MustacheTemplateEngine()

        );

        Spark.post("/login", ((request, response) -> {
                    String firstName = request.queryParams("firstName");
                    String lastName = request.queryParams("lastName");
                    String currentJob = request.queryParams("currentJob");
                    if ((firstName == null || lastName == null || currentJob == null) || (
                            firstName == "" || lastName == "" || currentJob == ""
                    )) {
                        throw new Exception("Incomplete login entries.");
                    }
                    if (users.containsKey(firstName.toLowerCase() + lastName.toLowerCase())) {
                        user = users.get(firstName.toLowerCase() + lastName.toLowerCase());
                        Session session = request.session();
                        session.attribute("userName", firstName.toLowerCase() + lastName.toLowerCase());

                        response.redirect("/");
                    } else {

                        newId++;
                        user = new User(firstName, lastName, currentJob, newId);
                        users.put(firstName.toLowerCase() + lastName.toLowerCase(), user);

                        Session session = request.session();
                        session.attribute("userName", firstName.toLowerCase() + lastName.toLowerCase());
                        response.redirect("");
                    }

                    return "";
                })
        );

        Spark.post("/logout", ((request, response) -> {
                    Session session = request.session();
                    session.invalidate();
                    response.redirect("/");
                    return "";
                })
        );

        Spark.post(
                "/create-job",
                ((request, response) -> {
                    Session session = request.session();
                    String userName = session.attribute("userName");
                    User user = users.get(userName);
                    String jobTitle = request.queryParams("job-title");
                    int jobSalary = Integer.parseInt(request.queryParams("job-salary"));
                    int jobYears = Integer.parseInt(request.queryParams("job-years"));
                    String jobLocation = request.queryParams("job-location");
                    Job job = new Job(listId, jobSalary, jobTitle, jobYears, jobLocation, user.getId(), "");
                    jobs.add(job);
                    listId++;

                    response.redirect("/");

                    return "";
                })
        );

        Spark.post(
                "/delete-job",
                ((request, response) -> {
                    Session session = request.session();
                    String name = session.attribute("userName");
                    User user = users.get(name);
                    int jobNum = Integer.parseInt(request.queryParams("delete"));
                    jobs.remove(jobNum);
                    response.redirect("/");
                    return "";
                })
        );
        Spark.get("/edit", ((request, response) -> {
                    Session session = request.session();
                    String name = session.attribute("userName");
                    User user = users.get(name);
                    currentId = Integer.parseInt(request.queryParams("editNum"));
                    return new ModelAndView(m, "edit.html");

                }),
                new MustacheTemplateEngine()
        );

        Spark.post(
                "/edit-job",
                ((request, response) -> {
                    Session session = request.session();
                    String name = session.attribute("userName");
                    User user = users.get(name);
                    int jobNum = currentId;
                    String jobTitle = request.queryParams("job-title");
                    int jobSalary = Integer.parseInt(request.queryParams("job-salary"));
                    int jobYears = Integer.parseInt(request.queryParams("job-years"));
                    String jobLocation = request.queryParams("job-location");
                    jobs.set(jobNum, new Job(jobSalary, jobTitle, jobYears, jobLocation, user.getId()));
                    response.redirect("/");
                    return "";
                })
        );

    }
}

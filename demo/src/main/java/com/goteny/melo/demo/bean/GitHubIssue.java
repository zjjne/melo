package com.goteny.melo.demo.bean;


import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GitHubIssue
{


    /**
     * url : https://api.github.com/repos/square/okhttp/issues/3571
     * repository_url : https://api.github.com/repos/square/okhttp
     * labels_url : https://api.github.com/repos/square/okhttp/issues/3571/labels{/name}
     * comments_url : https://api.github.com/repos/square/okhttp/issues/3571/comments
     * events_url : https://api.github.com/repos/square/okhttp/issues/3571/events
     * html_url : https://github.com/square/okhttp/pull/3571
     * id : 254691234
     * number : 3571
     * title : The WebSocketWriter is single-threaded.
     * user : {"login":"swankjesse","id":133019,"avatar_url":"https://avatars3.githubusercontent.com/u/133019?v=4","gravatar_id":"","url":"https://api.github.com/users/swankjesse","html_url":"https://github.com/swankjesse","followers_url":"https://api.github.com/users/swankjesse/followers","following_url":"https://api.github.com/users/swankjesse/following{/other_user}","gists_url":"https://api.github.com/users/swankjesse/gists{/gist_id}","starred_url":"https://api.github.com/users/swankjesse/starred{/owner}{/repo}","subscriptions_url":"https://api.github.com/users/swankjesse/subscriptions","organizations_url":"https://api.github.com/users/swankjesse/orgs","repos_url":"https://api.github.com/users/swankjesse/repos","events_url":"https://api.github.com/users/swankjesse/events{/privacy}","received_events_url":"https://api.github.com/users/swankjesse/received_events","type":"User","site_admin":false}
     * labels : [{"id":265665141,"url":"https://api.github.com/repos/square/okhttp/labels/websockets","name":"websockets","color":"fbca04","default":false}]
     * state : closed
     * locked : false
     * assignee : null
     * assignees : []
     * milestone : {"url":"https://api.github.com/repos/square/okhttp/milestones/24","html_url":"https://github.com/square/okhttp/milestone/24","labels_url":"https://api.github.com/repos/square/okhttp/milestones/24/labels","id":2488315,"number":24,"title":"3.9","description":"","creator":{"login":"swankjesse","id":133019,"avatar_url":"https://avatars3.githubusercontent.com/u/133019?v=4","gravatar_id":"","url":"https://api.github.com/users/swankjesse","html_url":"https://github.com/swankjesse","followers_url":"https://api.github.com/users/swankjesse/followers","following_url":"https://api.github.com/users/swankjesse/following{/other_user}","gists_url":"https://api.github.com/users/swankjesse/gists{/gist_id}","starred_url":"https://api.github.com/users/swankjesse/starred{/owner}{/repo}","subscriptions_url":"https://api.github.com/users/swankjesse/subscriptions","organizations_url":"https://api.github.com/users/swankjesse/orgs","repos_url":"https://api.github.com/users/swankjesse/repos","events_url":"https://api.github.com/users/swankjesse/events{/privacy}","received_events_url":"https://api.github.com/users/swankjesse/received_events","type":"User","site_admin":false},"open_issues":0,"closed_issues":27,"state":"closed","created_at":"2017-04-28T14:17:48Z","updated_at":"2017-09-04T22:02:01Z","due_on":"2017-09-07T07:00:00Z","closed_at":"2017-09-04T22:02:01Z"}
     * comments : 0
     * created_at : 2017-09-01T16:28:30Z
     * updated_at : 2017-09-04T20:18:37Z
     * closed_at : 2017-09-01T16:48:56Z
     * author_association : OWNER
     * pull_request : {"url":"https://api.github.com/repos/square/okhttp/pulls/3571","html_url":"https://github.com/square/okhttp/pull/3571","diff_url":"https://github.com/square/okhttp/pull/3571.diff","patch_url":"https://github.com/square/okhttp/pull/3571.patch"}
     * body : We were synchronizing to permit multiple writer threads. But that was a carry-over
     from the previous design where we supported multiple writer threads. With the
     current implementation only one thread writes at a time. This synchronization
     was not necessary.
     */

    private String url;
    private String repository_url;
    private String labels_url;
    private String comments_url;
    private String events_url;
    private String html_url;
    private int id;
    private int number;
    private String title;
    private UserBean user;
    private String state;
    private boolean locked;
    private Object assignee;
    private MilestoneBean milestone;
    private int comments;
    private String created_at;
    private String updated_at;
    private String closed_at;
    private String author_association;
    private PullRequestBean pull_request;
    private String body;
    private List<LabelsBean> labels;
    private List<?> assignees;

    public String getUrl()
    {
        return url;
    }

    public void setUrl(String url)
    {
        this.url = url;
    }

    public String getRepository_url()
    {
        return repository_url;
    }

    public void setRepository_url(String repository_url)
    {
        this.repository_url = repository_url;
    }

    public String getLabels_url()
    {
        return labels_url;
    }

    public void setLabels_url(String labels_url)
    {
        this.labels_url = labels_url;
    }

    public String getComments_url()
    {
        return comments_url;
    }

    public void setComments_url(String comments_url)
    {
        this.comments_url = comments_url;
    }

    public String getEvents_url()
    {
        return events_url;
    }

    public void setEvents_url(String events_url)
    {
        this.events_url = events_url;
    }

    public String getHtml_url()
    {
        return html_url;
    }

    public void setHtml_url(String html_url)
    {
        this.html_url = html_url;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public int getNumber()
    {
        return number;
    }

    public void setNumber(int number)
    {
        this.number = number;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public UserBean getUser()
    {
        return user;
    }

    public void setUser(UserBean user)
    {
        this.user = user;
    }

    public String getState()
    {
        return state;
    }

    public void setState(String state)
    {
        this.state = state;
    }

    public boolean isLocked()
    {
        return locked;
    }

    public void setLocked(boolean locked)
    {
        this.locked = locked;
    }

    public Object getAssignee()
    {
        return assignee;
    }

    public void setAssignee(Object assignee)
    {
        this.assignee = assignee;
    }

    public MilestoneBean getMilestone()
    {
        return milestone;
    }

    public void setMilestone(MilestoneBean milestone)
    {
        this.milestone = milestone;
    }

    public int getComments()
    {
        return comments;
    }

    public void setComments(int comments)
    {
        this.comments = comments;
    }

    public String getCreated_at()
    {
        return created_at;
    }

    public void setCreated_at(String created_at)
    {
        this.created_at = created_at;
    }

    public String getUpdated_at()
    {
        return updated_at;
    }

    public void setUpdated_at(String updated_at)
    {
        this.updated_at = updated_at;
    }

    public String getClosed_at()
    {
        return closed_at;
    }

    public void setClosed_at(String closed_at)
    {
        this.closed_at = closed_at;
    }

    public String getAuthor_association()
    {
        return author_association;
    }

    public void setAuthor_association(String author_association)
    {
        this.author_association = author_association;
    }

    public PullRequestBean getPull_request()
    {
        return pull_request;
    }

    public void setPull_request(PullRequestBean pull_request)
    {
        this.pull_request = pull_request;
    }

    public String getBody()
    {
        return body;
    }

    public void setBody(String body)
    {
        this.body = body;
    }

    public List<LabelsBean> getLabels()
    {
        return labels;
    }

    public void setLabels(List<LabelsBean> labels)
    {
        this.labels = labels;
    }

    public List<?> getAssignees()
    {
        return assignees;
    }

    public void setAssignees(List<?> assignees)
    {
        this.assignees = assignees;
    }

    public static class UserBean
    {
        /**
         * login : swankjesse
         * id : 133019
         * avatar_url : https://avatars3.githubusercontent.com/u/133019?v=4
         * gravatar_id :
         * url : https://api.github.com/users/swankjesse
         * html_url : https://github.com/swankjesse
         * followers_url : https://api.github.com/users/swankjesse/followers
         * following_url : https://api.github.com/users/swankjesse/following{/other_user}
         * gists_url : https://api.github.com/users/swankjesse/gists{/gist_id}
         * starred_url : https://api.github.com/users/swankjesse/starred{/owner}{/repo}
         * subscriptions_url : https://api.github.com/users/swankjesse/subscriptions
         * organizations_url : https://api.github.com/users/swankjesse/orgs
         * repos_url : https://api.github.com/users/swankjesse/repos
         * events_url : https://api.github.com/users/swankjesse/events{/privacy}
         * received_events_url : https://api.github.com/users/swankjesse/received_events
         * type : User
         * site_admin : false
         */

        private String login;
        private int id;
        private String avatar_url;
        private String gravatar_id;
        private String url;
        private String html_url;
        private String followers_url;
        private String following_url;
        private String gists_url;
        private String starred_url;
        private String subscriptions_url;
        private String organizations_url;
        private String repos_url;
        private String events_url;
        private String received_events_url;
        private String type;
        private boolean site_admin;

        public String getLogin()
        {
            return login;
        }

        public void setLogin(String login)
        {
            this.login = login;
        }

        public int getId()
        {
            return id;
        }

        public void setId(int id)
        {
            this.id = id;
        }

        public String getAvatar_url()
        {
            return avatar_url;
        }

        public void setAvatar_url(String avatar_url)
        {
            this.avatar_url = avatar_url;
        }

        public String getGravatar_id()
        {
            return gravatar_id;
        }

        public void setGravatar_id(String gravatar_id)
        {
            this.gravatar_id = gravatar_id;
        }

        public String getUrl()
        {
            return url;
        }

        public void setUrl(String url)
        {
            this.url = url;
        }

        public String getHtml_url()
        {
            return html_url;
        }

        public void setHtml_url(String html_url)
        {
            this.html_url = html_url;
        }

        public String getFollowers_url()
        {
            return followers_url;
        }

        public void setFollowers_url(String followers_url)
        {
            this.followers_url = followers_url;
        }

        public String getFollowing_url()
        {
            return following_url;
        }

        public void setFollowing_url(String following_url)
        {
            this.following_url = following_url;
        }

        public String getGists_url()
        {
            return gists_url;
        }

        public void setGists_url(String gists_url)
        {
            this.gists_url = gists_url;
        }

        public String getStarred_url()
        {
            return starred_url;
        }

        public void setStarred_url(String starred_url)
        {
            this.starred_url = starred_url;
        }

        public String getSubscriptions_url()
        {
            return subscriptions_url;
        }

        public void setSubscriptions_url(String subscriptions_url)
        {
            this.subscriptions_url = subscriptions_url;
        }

        public String getOrganizations_url()
        {
            return organizations_url;
        }

        public void setOrganizations_url(String organizations_url)
        {
            this.organizations_url = organizations_url;
        }

        public String getRepos_url()
        {
            return repos_url;
        }

        public void setRepos_url(String repos_url)
        {
            this.repos_url = repos_url;
        }

        public String getEvents_url()
        {
            return events_url;
        }

        public void setEvents_url(String events_url)
        {
            this.events_url = events_url;
        }

        public String getReceived_events_url()
        {
            return received_events_url;
        }

        public void setReceived_events_url(String received_events_url)
        {
            this.received_events_url = received_events_url;
        }

        public String getType()
        {
            return type;
        }

        public void setType(String type)
        {
            this.type = type;
        }

        public boolean isSite_admin()
        {
            return site_admin;
        }

        public void setSite_admin(boolean site_admin)
        {
            this.site_admin = site_admin;
        }
    }

    public static class MilestoneBean
    {
        /**
         * url : https://api.github.com/repos/square/okhttp/milestones/24
         * html_url : https://github.com/square/okhttp/milestone/24
         * labels_url : https://api.github.com/repos/square/okhttp/milestones/24/labels
         * id : 2488315
         * number : 24
         * title : 3.9
         * description :
         * creator : {"login":"swankjesse","id":133019,"avatar_url":"https://avatars3.githubusercontent.com/u/133019?v=4","gravatar_id":"","url":"https://api.github.com/users/swankjesse","html_url":"https://github.com/swankjesse","followers_url":"https://api.github.com/users/swankjesse/followers","following_url":"https://api.github.com/users/swankjesse/following{/other_user}","gists_url":"https://api.github.com/users/swankjesse/gists{/gist_id}","starred_url":"https://api.github.com/users/swankjesse/starred{/owner}{/repo}","subscriptions_url":"https://api.github.com/users/swankjesse/subscriptions","organizations_url":"https://api.github.com/users/swankjesse/orgs","repos_url":"https://api.github.com/users/swankjesse/repos","events_url":"https://api.github.com/users/swankjesse/events{/privacy}","received_events_url":"https://api.github.com/users/swankjesse/received_events","type":"User","site_admin":false}
         * open_issues : 0
         * closed_issues : 27
         * state : closed
         * created_at : 2017-04-28T14:17:48Z
         * updated_at : 2017-09-04T22:02:01Z
         * due_on : 2017-09-07T07:00:00Z
         * closed_at : 2017-09-04T22:02:01Z
         */

        private String url;
        private String html_url;
        private String labels_url;
        private int id;
        private int number;
        private String title;
        private String description;
        private CreatorBean creator;
        private int open_issues;
        private int closed_issues;
        private String state;
        private String created_at;
        private String updated_at;
        private String due_on;
        private String closed_at;

        public String getUrl()
        {
            return url;
        }

        public void setUrl(String url)
        {
            this.url = url;
        }

        public String getHtml_url()
        {
            return html_url;
        }

        public void setHtml_url(String html_url)
        {
            this.html_url = html_url;
        }

        public String getLabels_url()
        {
            return labels_url;
        }

        public void setLabels_url(String labels_url)
        {
            this.labels_url = labels_url;
        }

        public int getId()
        {
            return id;
        }

        public void setId(int id)
        {
            this.id = id;
        }

        public int getNumber()
        {
            return number;
        }

        public void setNumber(int number)
        {
            this.number = number;
        }

        public String getTitle()
        {
            return title;
        }

        public void setTitle(String title)
        {
            this.title = title;
        }

        public String getDescription()
        {
            return description;
        }

        public void setDescription(String description)
        {
            this.description = description;
        }

        public CreatorBean getCreator()
        {
            return creator;
        }

        public void setCreator(CreatorBean creator)
        {
            this.creator = creator;
        }

        public int getOpen_issues()
        {
            return open_issues;
        }

        public void setOpen_issues(int open_issues)
        {
            this.open_issues = open_issues;
        }

        public int getClosed_issues()
        {
            return closed_issues;
        }

        public void setClosed_issues(int closed_issues)
        {
            this.closed_issues = closed_issues;
        }

        public String getState()
        {
            return state;
        }

        public void setState(String state)
        {
            this.state = state;
        }

        public String getCreated_at()
        {
            return created_at;
        }

        public void setCreated_at(String created_at)
        {
            this.created_at = created_at;
        }

        public String getUpdated_at()
        {
            return updated_at;
        }

        public void setUpdated_at(String updated_at)
        {
            this.updated_at = updated_at;
        }

        public String getDue_on()
        {
            return due_on;
        }

        public void setDue_on(String due_on)
        {
            this.due_on = due_on;
        }

        public String getClosed_at()
        {
            return closed_at;
        }

        public void setClosed_at(String closed_at)
        {
            this.closed_at = closed_at;
        }

        public static class CreatorBean
        {
            /**
             * login : swankjesse
             * id : 133019
             * avatar_url : https://avatars3.githubusercontent.com/u/133019?v=4
             * gravatar_id :
             * url : https://api.github.com/users/swankjesse
             * html_url : https://github.com/swankjesse
             * followers_url : https://api.github.com/users/swankjesse/followers
             * following_url : https://api.github.com/users/swankjesse/following{/other_user}
             * gists_url : https://api.github.com/users/swankjesse/gists{/gist_id}
             * starred_url : https://api.github.com/users/swankjesse/starred{/owner}{/repo}
             * subscriptions_url : https://api.github.com/users/swankjesse/subscriptions
             * organizations_url : https://api.github.com/users/swankjesse/orgs
             * repos_url : https://api.github.com/users/swankjesse/repos
             * events_url : https://api.github.com/users/swankjesse/events{/privacy}
             * received_events_url : https://api.github.com/users/swankjesse/received_events
             * type : User
             * site_admin : false
             */

            private String login;
            private int id;
            private String avatar_url;
            private String gravatar_id;
            private String url;
            private String html_url;
            private String followers_url;
            private String following_url;
            private String gists_url;
            private String starred_url;
            private String subscriptions_url;
            private String organizations_url;
            private String repos_url;
            private String events_url;
            private String received_events_url;
            private String type;
            private boolean site_admin;

            public String getLogin()
            {
                return login;
            }

            public void setLogin(String login)
            {
                this.login = login;
            }

            public int getId()
            {
                return id;
            }

            public void setId(int id)
            {
                this.id = id;
            }

            public String getAvatar_url()
            {
                return avatar_url;
            }

            public void setAvatar_url(String avatar_url)
            {
                this.avatar_url = avatar_url;
            }

            public String getGravatar_id()
            {
                return gravatar_id;
            }

            public void setGravatar_id(String gravatar_id)
            {
                this.gravatar_id = gravatar_id;
            }

            public String getUrl()
            {
                return url;
            }

            public void setUrl(String url)
            {
                this.url = url;
            }

            public String getHtml_url()
            {
                return html_url;
            }

            public void setHtml_url(String html_url)
            {
                this.html_url = html_url;
            }

            public String getFollowers_url()
            {
                return followers_url;
            }

            public void setFollowers_url(String followers_url)
            {
                this.followers_url = followers_url;
            }

            public String getFollowing_url()
            {
                return following_url;
            }

            public void setFollowing_url(String following_url)
            {
                this.following_url = following_url;
            }

            public String getGists_url()
            {
                return gists_url;
            }

            public void setGists_url(String gists_url)
            {
                this.gists_url = gists_url;
            }

            public String getStarred_url()
            {
                return starred_url;
            }

            public void setStarred_url(String starred_url)
            {
                this.starred_url = starred_url;
            }

            public String getSubscriptions_url()
            {
                return subscriptions_url;
            }

            public void setSubscriptions_url(String subscriptions_url)
            {
                this.subscriptions_url = subscriptions_url;
            }

            public String getOrganizations_url()
            {
                return organizations_url;
            }

            public void setOrganizations_url(String organizations_url)
            {
                this.organizations_url = organizations_url;
            }

            public String getRepos_url()
            {
                return repos_url;
            }

            public void setRepos_url(String repos_url)
            {
                this.repos_url = repos_url;
            }

            public String getEvents_url()
            {
                return events_url;
            }

            public void setEvents_url(String events_url)
            {
                this.events_url = events_url;
            }

            public String getReceived_events_url()
            {
                return received_events_url;
            }

            public void setReceived_events_url(String received_events_url)
            {
                this.received_events_url = received_events_url;
            }

            public String getType()
            {
                return type;
            }

            public void setType(String type)
            {
                this.type = type;
            }

            public boolean isSite_admin()
            {
                return site_admin;
            }

            public void setSite_admin(boolean site_admin)
            {
                this.site_admin = site_admin;
            }
        }
    }

    public static class PullRequestBean
    {
        /**
         * url : https://api.github.com/repos/square/okhttp/pulls/3571
         * html_url : https://github.com/square/okhttp/pull/3571
         * diff_url : https://github.com/square/okhttp/pull/3571.diff
         * patch_url : https://github.com/square/okhttp/pull/3571.patch
         */

        private String url;
        private String html_url;
        private String diff_url;
        private String patch_url;

        public String getUrl()
        {
            return url;
        }

        public void setUrl(String url)
        {
            this.url = url;
        }

        public String getHtml_url()
        {
            return html_url;
        }

        public void setHtml_url(String html_url)
        {
            this.html_url = html_url;
        }

        public String getDiff_url()
        {
            return diff_url;
        }

        public void setDiff_url(String diff_url)
        {
            this.diff_url = diff_url;
        }

        public String getPatch_url()
        {
            return patch_url;
        }

        public void setPatch_url(String patch_url)
        {
            this.patch_url = patch_url;
        }
    }

    public static class LabelsBean
    {
        /**
         * id : 265665141
         * url : https://api.github.com/repos/square/okhttp/labels/websockets
         * name : websockets
         * color : fbca04
         * default : false
         */

        private int id;
        private String url;
        private String name;
        private String color;
        @SerializedName("default")
        private boolean defaultX;

        public int getId()
        {
            return id;
        }

        public void setId(int id)
        {
            this.id = id;
        }

        public String getUrl()
        {
            return url;
        }

        public void setUrl(String url)
        {
            this.url = url;
        }

        public String getName()
        {
            return name;
        }

        public void setName(String name)
        {
            this.name = name;
        }

        public String getColor()
        {
            return color;
        }

        public void setColor(String color)
        {
            this.color = color;
        }

        public boolean isDefaultX()
        {
            return defaultX;
        }

        public void setDefaultX(boolean defaultX)
        {
            this.defaultX = defaultX;
        }
    }
}

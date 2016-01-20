(set-env!
  :source-paths #{"src" "test"}
  :resource-paths #{"src" "test" "scss" "bower_components"}
  :wagons '[[s3-wagon-private "1.1.2"]]
  :repositories [["clojars" "http://clojars.org/repo/"]
                 ["maven-central" "http://repo1.maven.org/maven2/"]
                 ["my.datomic.com" {:url      "https://my.datomic.com/repo"
                                    :username (System/getenv "DATOMIC_USERNAME")
                                    :password (System/getenv "DATOMIC_PASSWORD")}]]
  :dependencies '[[adzerk/boot-cljs "1.7.170-3" :scope "test"]
                  #_[adzerk/boot-cljs-repl "0.2.0" :scope "test"]
                  [adzerk/boot-reload "0.4.2" :scope "test"]
                  [pandeiro/boot-http "0.7.0" :scope "test"]
                  [cljsjs/boot-cljsjs "0.5.0" :scope "test"]
                  [allgress/boot-tasks "0.2.3" :scope "test"]]
  :compiler-options {:compiler-stats true})

(require
  '[adzerk.boot-cljs :refer :all]
  #_'[adzerk.boot-cljs-repl :refer :all]
  '[adzerk.boot-reload :refer :all]
  '[allgress.boot-tasks :refer :all]
  '[pandeiro.boot-http :refer :all]
  '[cljsjs.boot-cljsjs :refer :all])

(set-project-deps!)

(default-task-options!)

(deftask web-dev
         "Developer workflow for web-component UX."
         []
         (comp
           (asset-paths :asset-paths #{"html" "bower_components"})
           (serve :dir "target/")
           (watch)
           #_(checkout :dependencies [['allgress/cereus "0.9.4"]
                                      ['freactive "0.3.0"]])
           (speak)
           (reload)
           (cljs)
           #_(copy)))
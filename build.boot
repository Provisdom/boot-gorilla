(set-env!
  :source-paths #{"src" "test"}
  :resource-paths #{"src" "test" "scss" "bower_components"}
  :dependencies '[[org.clojure/clojure "1.8.0"]
                  [gorilla-repl "0.3.6"]]
  :compiler-options {:compiler-stats true})

(def +version+ "0.1.0")

(require '[boot-gorilla.core :refer :all])

(task-options!
  pom {:project     'boot-gorilla
       :version     +version+
       :description "Use the Gorilla REPL as a task in your Boot project"
       :url         "https://github.com/mathias/boot-gorilla"
       :scm         {:url "https://github.com/mathias/boot-gorilla"}
       :license     {"Eclipse Public License" "http://www.eclipse.org/legal/epl-v10.html"}})

(deftask build
         []
         (comp (pom) (jar) (install)))
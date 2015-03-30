(defproject pg-demo "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [
    [org.clojure/clojure        "1.6.0"]
    [cooljure                   "0.1.25"]
    [org.clojure/java.jdbc      "0.3.6"]
    [java-jdbc/dsl              "0.1.1"]
    [honeysql                   "0.5.1"]
    [postgresql/postgresql      "8.4-702.jdbc4"]
    [com.climate/claypoole      "0.4.0"]
  ]
  :update :always
  :main ^:skip-aot pg-demo.core
  :target-path "target/%s"
  :resource-paths [ "resources/ojdbc7.jar" ]
  :profiles {:uberjar {:aot :all}}
  :jvm-opts ["-Xmx14g" "-server"] 
)

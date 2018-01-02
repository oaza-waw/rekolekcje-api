package pl.oaza.warszawa.dor.rekolekcje

databaseChangeLog {
  include(file: 'changelog/initial.groovy', relativeToChangelogFile: true)
  include(file: 'changelog/security.groovy', relativeToChangelogFile: true)
}

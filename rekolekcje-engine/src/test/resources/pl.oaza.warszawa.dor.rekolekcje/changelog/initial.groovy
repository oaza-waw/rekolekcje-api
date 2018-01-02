package pl.oaza.warszawa.dor.rekolekcje.changelog

databaseChangeLog {
  changeSet(id: '1511952250743-1', author: 'karkat (generated)') {
    createSequence(sequenceName: 'hibernate_sequence')
  }

  changeSet(id: '1511952250743-2', author: 'karkat (generated)') {
    createTable(tableName: 'participant') {
      column(name: 'id', type: 'BIGINT') {
        constraints(nullable: false)
      }
      column(name: 'address', type: 'VARCHAR(255)')
      column(name: 'first_name', type: 'VARCHAR(255)')
      column(name: 'last_name', type: 'VARCHAR(255)')
      column(name: 'parish', type: 'VARCHAR(255)')
      column(name: 'pesel', type: 'BIGINT') {
        constraints(nullable: false)
      }
    }
  }

  changeSet(id: '1511952250743-3', author: 'karkat (generated)') {
    addPrimaryKey(columnNames: 'id', constraintName: 'participant_pkey', tableName: 'participant')
  }
}

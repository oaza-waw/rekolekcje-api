package pl.oaza.warszawa.dor.rekolekcje.changelog

databaseChangeLog {
  changeSet(id: 'security-1', author: 'qiubix (generated)') {
    createSequence(sequenceName: 'authority_seq')
  }

  changeSet(id: 'security-2', author: 'qiubix (generated)') {
    createSequence(sequenceName: 'user_seq')
  }

  changeSet(id: 'security-3', author: 'qiubix (generated)') {
    createTable(tableName: 'authority') {
      column(name: 'id', type: 'BIGINT') {
        constraints(nullable: false)
      }
      column(name: 'name', type: 'VARCHAR(50)') {
        constraints(nullable: false)
      }
    }
  }

  changeSet(id: 'security-4', author: 'qiubix (generated)') {
    createTable(tableName: 'user_authority') {
      column(name: 'user_id', type: 'BIGINT') {
        constraints(nullable: false)
      }
      column(name: 'authority_id', type: 'BIGINT') {
        constraints(nullable: false)
      }
    }
  }

  changeSet(id: 'security-5', author: 'qiubix (generated)') {
    createTable(tableName: 'users') {
      column(name: 'id', type: 'BIGINT') {
        constraints(nullable: false)
      }
      column(name: 'email', type: 'VARCHAR(50)') {
        constraints(nullable: false)
      }
      column(name: 'enabled', type: 'BOOLEAN') {
        constraints(nullable: false)
      }
      column(name: 'firstname', type: 'VARCHAR(50)') {
        constraints(nullable: false)
      }
      column(name: 'lastpasswordresetdate', type: 'TIMESTAMP(6) WITHOUT TIME ZONE') {
        constraints(nullable: false)
      }
      column(name: 'lastname', type: 'VARCHAR(50)') {
        constraints(nullable: false)
      }
      column(name: 'password', type: 'VARCHAR(100)') {
        constraints(nullable: false)
      }
      column(name: 'username', type: 'VARCHAR(50)') {
        constraints(nullable: false)
      }
    }
  }

  changeSet(id: 'security-6', author: 'qiubix (generated)') {
    addPrimaryKey(columnNames: 'id', constraintName: 'authority_pkey', tableName: 'authority')
  }

  changeSet(id: 'security-7', author: 'qiubix (generated)') {
    addPrimaryKey(columnNames: 'id', constraintName: 'users_pkey', tableName: 'users')
  }

  changeSet(id: 'security-8', author: 'qiubix (generated)') {
    addUniqueConstraint(
        columnNames: 'username',
        constraintName: 'uk_r43af9ap4edm43mmtq01oddj6',
        tableName: 'users'
    )
  }

  changeSet(id: 'security-9', author: 'qiubix (generated)') {
    addForeignKeyConstraint(
        baseColumnNames: 'authority_id',
        baseTableName: 'user_authority',
        constraintName: 'fkgvxjs381k6f48d5d2yi11uh89',
        deferrable: false,
        initiallyDeferred: false,
        onDelete: 'NO ACTION',
        onUpdate: 'NO ACTION',
        referencedColumnNames: 'id',
        referencedTableName: 'authority'
    )
  }

  changeSet(id: 'security-10', author: 'qiubix (generated)') {
    addForeignKeyConstraint(
        baseColumnNames: 'user_id',
        baseTableName: 'user_authority',
        constraintName: 'fkhi46vu7680y1hwvmnnuh4cybx',
        deferrable: false,
        initiallyDeferred: false,
        onDelete: 'NO ACTION',
        onUpdate: 'NO ACTION',
        referencedColumnNames: 'id',
        referencedTableName: 'users'
    )
  }

  changeSet(id: 'security-11', author: 'qiubix (generated)') {
    insert(tableName: "users") {
      column(name: 'id', value: 1)
      column(name: 'username', value: 'admin')
//      column(name: 'password', value: 'admin')
      column(name: 'password', value: '$2a$08$lDnHPz7eUkSi6ao14Twuau08mzhWrL4kyZGGU5xfiGALO/Vxd5DOi')
      column(name: 'firstname', value: 'admin')
      column(name: 'lastname', value: 'admin')
      column(name: 'email', value: 'admin@mail.com')
      column(name: 'enabled', value: true)
      column(name: 'lastpasswordresetdate', value: '2016-01-01')
    }
    insert(tableName: "users") {
      column(name: 'id', value: 2)
      column(name: 'username', value: 'user')
      column(name: 'password', value: '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC')
      column(name: 'firstname', value: 'user')
      column(name: 'lastname', value: 'user')
      column(name: 'email', value: 'user@mail.com')
      column(name: 'enabled', value: true)
      column(name: 'lastpasswordresetdate', value: '2016-01-01')
    }
    insert(tableName: "users") {
      column(name: 'id', value: 3)
      column(name: 'username', value: 'disabled')
      column(name: 'password', value: '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC')
      column(name: 'firstname', value: 'disabled')
      column(name: 'lastname', value: 'disabled')
      column(name: 'email', value: 'disabled@mail.com')
      column(name: 'enabled', value: false)
      column(name: 'lastpasswordresetdate', value: '2016-01-01')
    }
    insert(tableName: 'authority') {
      column(name: 'id', value: 1)
      column(name: 'name', value: 'ROLE_USER')
    }
    insert(tableName: 'authority') {
      column(name: 'id', value: 2)
      column(name: 'name', value: 'ROLE_ADMIN')
    }
    insert(tableName: 'user_authority') {
      column(name: 'user_id', value: 1)
      column(name: 'authority_id', value: 1)
    }
    insert(tableName: 'user_authority') {
      column(name: 'user_id', value: 1)
      column(name: 'authority_id', value: 2)
    }
    insert(tableName: 'user_authority') {
      column(name: 'user_id', value: 2)
      column(name: 'authority_id', value: 1)
    }
    insert(tableName: 'user_authority') {
      column(name: 'user_id', value: 3)
      column(name: 'authority_id', value: 1)
    }
  }

}

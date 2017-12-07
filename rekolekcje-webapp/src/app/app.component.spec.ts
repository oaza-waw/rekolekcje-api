import { TestBed, async } from '@angular/core/testing';

import { AppComponent } from './app.component';
import { ParticipantsModule } from './participants/participants.module';
import { RouterTestingModule } from '@angular/router/testing';
import { NavigationModule } from './navigation/navigation.module';
import { HomeModule } from './home/home.module';

describe('AppComponent', () => {
  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [
        ParticipantsModule,
        NavigationModule,
        HomeModule,
        RouterTestingModule
      ],
      declarations: [
        AppComponent
      ],
      providers: [
      ]
    }).compileComponents();
  }));

  it('should create the app', async(() => {
    const fixture = TestBed.createComponent(AppComponent);
    const app = fixture.debugElement.componentInstance;
    expect(app).toBeTruthy();
  }));

  it(`should have as title 'Rekolekcje'`, async(() => {
    const fixture = TestBed.createComponent(AppComponent);
    const app = fixture.debugElement.componentInstance;
    expect(app.title).toEqual('Rekolekcje');
  }));
});

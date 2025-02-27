package com.theopensourcefamily.chessclock

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jakewharton.rxbinding3.view.clicks
import io.reactivex.Observable
import kotlinx.android.synthetic.main.activity_clocks.*

class ClocksActivity : AppCompatActivity(), ClocksView {

  val presenter = ClocksPresenter() // maybe DI here, with koin or wathever

  override val userInteractions: Observable<ClocksView.Interaction>
    get() = Observable.merge(
      blackClock.clicks().map { ClocksView.Interaction.BlackPressed },
      whiteClock.clicks().map { ClocksView.Interaction.WhitePressed }
    )

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_clocks)

    presenter.bindView(this)
  }

  override fun onDestroy() {
    super.onDestroy()
    presenter.unbind()
  }
  override fun render() {
    // Just to put something in the clocks right now
    whiteClock.text = "5:00"
    blackClock.text = "5:00"
  }
}

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.checkpoint.CheckpointFactory as CheckpointFactory
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testcase.TestCaseFactory as TestCaseFactory
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testdata.TestDataFactory as TestDataFactory
import com.kms.katalon.core.testobject.ObjectRepository as ObjectRepository
import com.kms.katalon.core.testobject.TestObject as TestObject

import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile

import internal.GlobalVariable as GlobalVariable

import com.kms.katalon.core.annotation.SetUp
import com.kms.katalon.core.annotation.SetupTestCase
import com.kms.katalon.core.annotation.TearDown
import com.kms.katalon.core.annotation.TearDownTestCase
import com.kms.katalon.core.logging.KeywordLogger
import java.text.SimpleDateFormat
import java.util.Date
import java.io.FileWriter
import java.io.File

KeywordLogger log = new KeywordLogger()

def testResults = []

def saveToCSV(String filePath, List<List> data) {
    File file = new File(filePath)
    file.parentFile.mkdirs()
    FileWriter writer = new FileWriter(file)

    data.each { row ->
        writer.append(row.join(",")).append("\n")
    }

    writer.flush()
    writer.close()
    log.logInfo("✅ Laporan CSV disimpan di: " + filePath)
}

/**
 * Setup test suite environment.
 */
@SetUp(skipped = false)
def setUp() {
    log.logInfo("🚀 Memulai Test Suite: Weather_Pollution_Test_Suite")
}

/**
 * Clean test suites environment.
 */
@TearDown(skipped = false)
def tearDown() {
    log.logInfo("✅ Test Suite selesai. Semua hasil tes direkam.")
    
    // Simpan hasil ke CSV setelah Test Suite selesai
    saveToCSV("Reports/Weather_Pollution_Test_Suite/Test_Suite_Summary.csv", testResults)
}

/**
 * Run before each test case starts.
 */
@SetupTestCase(skipped = false)
def setupTestCase() {
    log.logInfo("🔄 Memulai Test Case baru.")
}

/**
 * Run after each test case ends.
 */
@TearDownTestCase(skipped = false)
def tearDownTestCase() {
    log.logInfo("✅ Test Case selesai.")
}

/**
 * References:
 * Groovy tutorial page: http://docs.groovy-lang.org/next/html/documentation/
 */

@TearDownTestCase(skipped = false)
def tearDownTestCase() {
	log.logInfo("✅ Test Case selesai.")
	
	// Tangkap hasil eksekusi Test Case
	def testCaseContext = com.kms.katalon.cskipped ore.context.TestCaseContext.currentContext
	def testCaseName = testCaseContext.getTestCaseId()
	def testCaseStatus = testCaseContext.getTestCaseStatus()
	
	// Simpan ke dalam testResults
	testResults.add([testCaseName, testCaseStatus, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())])
}
